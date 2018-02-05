package code.helpers;

import code.Constants;
import code.exceptions.AuditingException;
import code.exceptions.ExceptionType;
import code.model.AuditLog;
import code.model.TransactionType;
import com.authenteq.api.AssetsApi;
import com.authenteq.api.StatusException;
import com.authenteq.api.StatusesApi;
import com.authenteq.api.TransactionsApi;
import com.authenteq.builders.BigchainDbConfigBuilder;
import com.authenteq.builders.BigchainDbTransactionBuilder;
import com.authenteq.constants.Operations;
import com.authenteq.model.Assets;
import com.authenteq.model.Status;
import com.authenteq.model.StatusCode;
import com.authenteq.model.Transaction;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;

import java.io.IOException;
import java.security.KeyPair;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    This class contains te logic required for interacting with the Bigchain DB server
 */
public class BigchainDBHelper
{
    static {
        BigchainDbConfigBuilder
                .baseUrl(Constants.BIGCHAIN_DB_BASE_URL)
                .setup();
    }

    /*
        Creates a new Audit Log transaction in the Bigchain DB server
     */
    public static String createAuditLog(AuditLog auditLog) throws AuditingException
    {
        try {
            KeyPair securityKeys = generateKeys();

            Transaction transaction = BigchainDbTransactionBuilder.init()
                    .addAsset(Constants.DATE_FIELD_NAME, auditLog.getTimestamp())
                    .addAsset(Constants.SYSTEM_FIELD_NAME, auditLog.getSystem())
                    .addAsset(Constants.USERNAME_FIELD_NAME, auditLog.getUsername())
                    .addAsset(Constants.TYPE_FIELD_NAME, auditLog.getTransactionType().getValue())
                    .addAsset(Constants.MESSAGE_FIELD_NAME, auditLog.getMessage())
                    .operation(Operations.CREATE)
                    .buildAndSign((EdDSAPublicKey) securityKeys.getPublic(), (EdDSAPrivateKey) securityKeys.getPrivate())
                    .sendTransaction();

            return transaction.getId();
        } catch (IOException exception) {
            throw new AuditingException(exception,
                    ExceptionType.SERVER_ERROR,
                    String.format(Constants.CREATE_TRANSACTION_ERROR_MESSAGE, exception.getMessage()));
        }
    }

    /*
        Gets an Audit Log based on it's transaction id
     */
    public static AuditLog getAuditLogById(String id) throws AuditingException
    {
        try {
            //Check if transaction can be found
            if (!hasTransactionBeenProcessed(id)) {
                throw new AuditingException(
                        ExceptionType.TRANSACTION_NOT_READY,
                        String.format(Constants.TRANSACTION_NOT_READY_ERROR_MESSAGE, id));
            }

            //Get Transaction
            Transaction transaction = TransactionsApi.getTransactionById(id);
            Map<String, String> data = transaction.getAsset().getData();

            return convertToAuditLog(transaction.getId(), data);
        } catch (IOException exception) {
            throw new AuditingException(exception,
                    ExceptionType.SERVER_ERROR,
                    String.format(String.format(Constants.GET_TRANSACTION_ERROR_MESSAGE, id, exception.getMessage())));
        }
    }

    /*
        Checks to see if a transaction has been processed by Bigchain DB
     */
    public static boolean hasTransactionBeenProcessed(String id) throws AuditingException
    {
        try {
            Status status = StatusesApi.getTransactionStatus(id);

            return status.getStatus() == StatusCode.VALID;
        } catch (StatusException | IOException exception) {
            if (exception.getMessage().contains("404")) {
                throw new AuditingException(exception,
                        ExceptionType.NO_DATA,
                        String.format(Constants.TRANSACTION_NOT_FOUND_ERROR_MESSAGE, id));
            }

            throw new AuditingException(exception,
                    ExceptionType.SERVER_ERROR,
                    String.format(Constants.GET_STATUS_ERROR_MESSAGE, id, exception.getMessage()));
        }
    }

    /*
        Retrieves Audit Logs based on search term provided
     */
    public static List<AuditLog> getAuditLogs(String searchTerm) throws AuditingException
    {
        try {
            Assets assets = AssetsApi.getAssets(searchTerm);

            if (assets.size() == 0) {
                throw new AuditingException(ExceptionType.NO_DATA, Constants.NO_TRANSACTIONS_FOUND_ERROR_MESSAGE);
            }

            return assets.getAssets().stream()
                    .map(asset -> convertToAuditLog(asset.getId(), asset.getData()))
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new AuditingException(exception, ExceptionType.SERVER_ERROR,
                    String.format(Constants.GET_TRANSACTIONS_ERROR_MESSAGE, searchTerm, exception.getMessage()));
        }
    }

    /*
        Generates the Security Keys needed to send a transaction
     */
    private static KeyPair generateKeys()
    {
        net.i2p.crypto.eddsa.KeyPairGenerator edDsaKpg = new net.i2p.crypto.eddsa.KeyPairGenerator();
        return edDsaKpg.generateKeyPair();
    }

    /*
        Converts a String Map to a Audit Log Object
     */
    private static AuditLog convertToAuditLog(String id, Map<String, String> data)
    {
        AuditLog auditLog = new AuditLog();
        auditLog.setId(id);
        auditLog.setDate(data.get(Constants.DATE_FIELD_NAME));
        auditLog.setSystem(data.get(Constants.SYSTEM_FIELD_NAME));
        auditLog.setUsername(data.get(Constants.USERNAME_FIELD_NAME));
        auditLog.setTransactionType(TransactionType.get(data.get(Constants.TYPE_FIELD_NAME)));
        auditLog.setMessage(data.get(Constants.MESSAGE_FIELD_NAME));

        return auditLog;
    }


}

