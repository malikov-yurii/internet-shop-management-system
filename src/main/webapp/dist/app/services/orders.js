"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var Observable_1 = require("rxjs/Observable");
require("rxjs/add/operator/do");
require("rxjs/add/observable/of");
var api_1 = require("./api");
var store_helper_1 = require("./store-helper");
var search_1 = require("./search");
var models_1 = require("../models");
var OrderService = (function () {
    function OrderService(api, storeHelper, searchService) {
        this.api = api;
        this.storeHelper = storeHelper;
        this.searchService = searchService;
        this.ordersPath = 'order';
        this.productsPath = 'orderItemDtos';
    }
    OrderService.prototype.purgeStore = function () {
        this.storeHelper.update(this.ordersPath, []);
    };
    OrderService.prototype.getOrders = function (start, length) {
        var _this = this;
        return this.api.get("/" + this.ordersPath + "?pageNumber=" + start + "&pageCapacity=" + length)
            .do(function (resp) {
            _this.storeHelper.update('order', resp.elements);
        });
    };
    OrderService.prototype.addOrder = function () {
        var _this = this;
        var newOrder = new models_1.Order();
        var newOrderId = newOrder.id;
        var newOrderItemId = newOrder[this.productsPath][0].id;
        this.storeHelper.add(this.ordersPath, newOrder);
        return this.api.post(this.ordersPath)
            .do(function (resp) {
            _this.storeHelper.findAndUpdate(_this.ordersPath, newOrderId, 'id', resp.orderId);
            _this.storeHelper.findDeepAndUpdate(_this.ordersPath, resp.orderId, _this.productsPath, newOrderItemId, 'id', resp.orderItemId);
        });
    };
    OrderService.prototype.deleteOrder = function (orderId) {
        this.storeHelper.findAndDelete(this.ordersPath, orderId);
        return this.api.apiDelete(this.ordersPath + "/" + orderId);
    };
    // Changing order info common field (e.g., firstName, phoneNumber)
    OrderService.prototype.updateInfoField = function (orderId, fieldName, value) {
        return this.api.put(this.ordersPath + "/" + orderId, (_a = {},
            _a[fieldName] = value,
            _a));
        var _a;
    };
    // Changing order info INPUT (e.g., Status, Payment type)
    OrderService.prototype.updateInfoInput = function (orderId, fieldName, value) {
        this.storeHelper.findAndUpdate(this.ordersPath, orderId, fieldName, value);
        return this.api.put(this.ordersPath + "/" + orderId, (_a = {}, _a[fieldName] = value, _a));
        var _a;
    };
    OrderService.prototype.autocompleteInfo = function (orderId, object) {
        this.storeHelper.findAndUpdateWithObject(this.ordersPath, orderId, object);
        this.api.put(this.ordersPath + "/" + orderId + "/set-customer", { customerId: object.customerId }).subscribe();
    };
    OrderService.prototype.addProduct = function (orderId) {
        var _this = this;
        var newProduct = new models_1.Product();
        var newProductId = newProduct.id;
        this.storeHelper.findDeepAndAdd(this.ordersPath, orderId, this.productsPath, newProduct);
        this.api.post("order-item/create-empty-for/" + orderId)
            .subscribe(function (productId) {
            _this.storeHelper.findDeepAndUpdate(_this.ordersPath, orderId, _this.productsPath, newProductId, 'id', productId);
        });
    };
    // Changing order item editable field (e.g., name, price)
    OrderService.prototype.updateProductField = function (orderId, productId, fieldName, value) {
        var _this = this;
        return this.api.put("order-item/" + productId, (_a = {}, _a[fieldName] = value, _a)).do(function (data) {
            if (data) {
                _this.storeHelper.findAndUpdate(_this.ordersPath, orderId, 'totalSum', data);
            }
        });
        var _a;
    };
    // Changing order item INPUT (quantity)
    OrderService.prototype.updateProductInput = function (orderId, productId, fieldName, value) {
        var _this = this;
        this.storeHelper.findDeepAndUpdate(this.ordersPath, orderId, this.productsPath, productId, fieldName, value);
        this.api.put("order-item/" + productId, (_a = {}, _a[fieldName] = value, _a)).subscribe(function (data) {
            if (data) {
                _this.storeHelper.findAndUpdate(_this.ordersPath, orderId, 'totalSum', data);
            }
        });
        var _a;
    };
    OrderService.prototype.autocompleteProduct = function (orderId, productId, data) {
        data['quantity'] = 1;
        this.storeHelper.findDeepAndUpdateWithObject(this.ordersPath, orderId, this.productsPath, productId, data);
        if (data.productVariationId) {
            this.api.put("order-item/" + productId, { productVariationId: data.productVariationId }).subscribe();
        }
        else {
            this.api.put("order-item/" + productId, { productId: data.productId }).subscribe();
        }
    };
    OrderService.prototype.deleteProduct = function (orderId, productId) {
        this.storeHelper.findDeepAndDelete(this.ordersPath, orderId, this.productsPath, productId);
        return this.api.apiDelete("order-item/" + productId);
    };
    OrderService.prototype.getCustomer = function (customerId) {
        return this.api.get("customer/" + customerId);
    };
    OrderService.prototype.saveCustomer = function (customerId, customerInfo) {
        return this.api.put("customer/" + customerId, customerInfo);
    };
    OrderService.prototype.persistCustomer = function (orderId) {
        var _this = this;
        this.api.post("customer/persist-customer-from-order/" + orderId)
            .subscribe(function (customerId) {
            _this.storeHelper.findAndUpdate(_this.ordersPath, orderId, 'customerId', customerId);
        });
    };
    OrderService.prototype.list = function (searchQuery, page, length) {
        if (searchQuery === void 0) { searchQuery = ''; }
        if (page === void 0) { page = 1; }
        if (length === void 0) { length = 10; }
        var orderResult = this.searchService.search(this.storeHelper.get(this.ordersPath), searchQuery);
        var orderResultPage = orderResult.slice(0, length);
        return Observable_1.Observable.of({
            orders: orderResultPage,
            filtered: orderResult.length
        });
    };
    OrderService.prototype.autocomplete = function (types, term) {
        if (types[1] === 'lastName' || types[1] === 'firstName') {
            return this.api.get("customer/autocomplete-by-last-name-mask/" + term);
        }
        else if (types[1] === 'phoneNumber') {
            return this.api.get("customer/autocomplete-by-phone-number-mask/" + term);
        }
        else if (types[1] === 'city') {
            return this.api.get("customer/autocomplete-by-city-mask/" + term);
        }
        else if (types[0] === 'product') {
            return this.api.get("order-item/autocomplete-by-product-name/" + term);
        }
    };
    OrderService.prototype.camelCaseToDash = function (str) {
        return str.replace(/([A-Z])/g, function (g) { return "-" + g[0].toLowerCase(); });
    };
    // @TODO remove this
    OrderService.prototype.getStore = function () {
        this.storeHelper.onGetState();
    };
    OrderService.prototype.getAllOrders = function () {
        return this.api.get(this.ordersPath + "?start=0&length=10000");
    };
    return OrderService;
}());
OrderService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [api_1.ApiService,
        store_helper_1.StoreHelper,
        search_1.SearchService])
], OrderService);
exports.OrderService = OrderService;
//# sourceMappingURL=orders.js.map