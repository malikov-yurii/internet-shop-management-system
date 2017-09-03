"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Order = (function () {
    function Order() {
        this.id = "0-" + Order.count++;
        this.customerId = 0;
        this.lastName = '';
        this.firstName = '';
        this.phoneNumber = '';
        this.city = '';
        this.postOffice = '';
        this.totalSum = 0;
        this.paymentType = 'NP';
        this.date = new Date();
        this.status = 'NEW';
        this.comment = '';
        this.orderItemDtos = [new Product()];
    }
    return Order;
}());
Order.count = 0;
exports.Order = Order;
var Product = (function () {
    function Product() {
        this.id = "0-" + Product.count++;
        this.productId = 0;
        this.productVariationId = 0;
        this.name = '';
        this.categories = [''];
        this.quantity = '0';
        this.price = '0';
        this.supplier = '';
    }
    return Product;
}());
Product.count = 0;
exports.Product = Product;
var AutocompleteItem = (function () {
    function AutocompleteItem() {
        this.label = '';
    }
    return AutocompleteItem;
}());
exports.AutocompleteItem = AutocompleteItem;
var StaticDATA = (function () {
    function StaticDATA() {
    }
    return StaticDATA;
}());
StaticDATA.infoBlocks = {
    status: ['SHP', 'WFP', 'OK', 'NEW', 'NOT'],
    paymentType: ['PB', 'SV', 'NP']
};
StaticDATA.autocompleteBlocks = ['lastName', 'phoneNumber', 'city', 'name'];
StaticDATA.keycodesNotToAutocomplete = [9, 13, 16, 17, 18, 20]; // Tab, Enter, Shift, Ctrl, Alt, Caps Lock
StaticDATA.sessionTime = 235 * 60 * 1000; // minutes
exports.StaticDATA = StaticDATA;
//# sourceMappingURL=models.js.map