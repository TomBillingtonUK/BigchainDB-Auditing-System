exports.ids = [2];
exports.modules = {

/***/ "./src/routes/Account/Account.js":
/*!***************************************!*\
  !*** ./src/routes/Account/Account.js ***!
  \***************************************/
/*! exports provided: default */
/*! exports used: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_react__ = __webpack_require__(/*! react */ "react");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_react___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_react__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_styled_components__ = __webpack_require__(/*! styled-components */ "styled-components");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_styled_components___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_styled_components__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_material_ui_Card__ = __webpack_require__(/*! material-ui/Card */ "material-ui/Card");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_material_ui_Card___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_material_ui_Card__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_material_ui_Typography__ = __webpack_require__(/*! material-ui/Typography */ "material-ui/Typography");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_material_ui_Typography___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_material_ui_Typography__);
var _jsxFileName = "/home/uni/BigchainDB-Auditing-System/ui/src/routes/Account/Account.js";

/**
 * React Starter Kit for Firebase and GraphQL
 * https://github.com/kriasoft/react-firebase-starter
 * Copyright (c) 2015-present Kriasoft | MIT License
 */




const Container = __WEBPACK_IMPORTED_MODULE_1_styled_components___default.a.div`
  max-width: 600px;
  box-sizing: border-box;
  margin: 0 auto;
`;
const Content = __WEBPACK_IMPORTED_MODULE_1_styled_components___default()(__WEBPACK_IMPORTED_MODULE_2_material_ui_Card___default.a)`
  padding: 1em 2em;
  margin: 2em 0;
`;

class Home extends __WEBPACK_IMPORTED_MODULE_0_react___default.a.Component {
  render() {
    return __WEBPACK_IMPORTED_MODULE_0_react___default.a.createElement(Container, {
      __source: {
        fileName: _jsxFileName,
        lineNumber: 28
      },
      __self: this
    }, __WEBPACK_IMPORTED_MODULE_0_react___default.a.createElement(Content, {
      __source: {
        fileName: _jsxFileName,
        lineNumber: 29
      },
      __self: this
    }, __WEBPACK_IMPORTED_MODULE_0_react___default.a.createElement(__WEBPACK_IMPORTED_MODULE_3_material_ui_Typography___default.a, {
      type: "headline",
      gutterBottom: true,
      __source: {
        fileName: _jsxFileName,
        lineNumber: 30
      },
      __self: this
    }, "My Account"), __WEBPACK_IMPORTED_MODULE_0_react___default.a.createElement(__WEBPACK_IMPORTED_MODULE_3_material_ui_Typography___default.a, {
      type: "body1",
      paragraph: true,
      __source: {
        fileName: _jsxFileName,
        lineNumber: 33
      },
      __self: this
    }, "Welcome, ", this.props.user && this.props.user.displayName, "!")));
  }

}

/* harmony default export */ __webpack_exports__["a"] = (Home);

/***/ }),

/***/ "./src/routes/Account/index.js":
/*!*************************************!*\
  !*** ./src/routes/Account/index.js ***!
  \*************************************/
/*! exports provided: default */
/*! all exports used */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__Account__ = __webpack_require__(/*! ./Account */ "./src/routes/Account/Account.js");
/* harmony reexport (binding) */ __webpack_require__.d(__webpack_exports__, "default", function() { return __WEBPACK_IMPORTED_MODULE_0__Account__["a"]; });
/**
 * React Starter Kit for Firebase and GraphQL
 * https://github.com/kriasoft/react-firebase-starter
 * Copyright (c) 2015-present Kriasoft | MIT License
 */


/***/ })

};;
//# sourceMappingURL=account.chunk.js.map