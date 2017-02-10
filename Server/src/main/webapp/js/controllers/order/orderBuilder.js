function Order() {
    this.client = {id: 0, name: ''};
    this.items = [];
}
function OrderItem() {
    this.quantity = 0;
    this.id = 0;
    this.product = {
        fabric: {id: 0, code: '', longname: ''},
        color: {id: 0, name: ''},
        stripe: null,
        stripeCombination: null
    };
    this.accesories = [];

    this.getProductName = function () {
        var name = this.product.fabric.code + " " + this.product.fabric.longname;
        name = name + " " + this.product.color.name;
        if (this.product.stripe != null) {
            name = name + " " + this.product.stripe.name;
        }
        if (this.product.stripeCombination != null) {
            name = name + " " + this.product.stripeCombination.name;
        }
        return name;
    };
    this.getCuelloItem = function () {
        for (var i = this.accesories.length - 1; i >= 0; i--) {
            if (this.accesories[i].product.fabric.cuello) {
                return this.accesories[i];
            }
        }
        return null;
    };
    this.getTiraItem = function () {
        for (var i = this.accesories.length - 1; i >= 0; i--) {
            if (this.accesories[i].product.fabric.tira) {
                return this.accesories[i];
            }
        }
        return null;
    };
    this.getPunoItem = function () {
        for (var i = this.accesories.length - 1; i >= 0; i--) {
            if (this.accesories[i].product.fabric.puno) {
                return this.accesories[i];
            }
        }
        return null;
    };
    this.getItemType = function () {

        if (this.product.fabric.puno) {
            return "puno";
        }
        if (this.product.fabric.cuello) {
            return "cuello";
        }
        if (this.product.fabric.tira) {
            return "tira";
        }
        return "main";
    };

}
function OrderBuilder() {
    this.order = new Order();

    this.item = new OrderItem();

    this.itemPuno = null;
    this.itemCuello = null;
    this.itemTira = null;
    this.setItemQuantity = function (quantity, type) {
        if (type == 'tira') {
            this.getItemTira().quantity = quantity;
        } else if (type == 'cuello') {
            this.getItemCuello().quantity = quantity;
        } else if (type == 'puno') {
            this.getItemPuno().quantity = quantity;
        } else if (type == 'main') {
            this.item.quantity = quantity;
        }
    };

    this.getItemPuno = function () {
        if (this.itemPuno == null) {
            this.itemPuno = new OrderItem();
        }
        return this.itemPuno;
    };
    this.getItemCuello = function () {
        if (this.itemCuello == null) {
            this.itemCuello = new OrderItem();
        }
        return this.itemCuello;
    };
    this.getItemTira = function () {
        if (this.itemTira == null) {
            this.itemTira = new OrderItem();
        }
        return this.itemTira;
    };

    this.setClient = function (id, name) {
        this.order.client.id = id;
        this.order.client.name = name;
    };

    this.setItemId = function (id, type) {
        if (type == 'tira') {
            this.getItemTira().id = id;
        } else if (type == 'cuello') {
            this.getItemCuello().id = id;
        } else if (type == 'puno') {
            this.getItemPuno().id = id;
        } else if (type == 'main') {
            this.item.id = id;
        }
    };
    this.setColor = function (id, name) {
        this.item.product.color.id = id;
        this.item.product.color.name = name;
    };
    this.setFabric = function (id, code, longname, type) {
        var fabric = {};
        fabric.id = id;
        fabric.code = code;
        fabric.longname = longname;
        if (type == 'tira') {
            this.getItemTira().product.fabric = fabric;
            this.getItemTira().product.fabric.tira = true;
        } else if (type == 'cuello') {
            this.getItemCuello().product.fabric = fabric;
            this.getItemCuello().product.fabric.cuello = true;
        } else if (type == 'puno') {
            this.getItemPuno().product.fabric = fabric;
            this.getItemPuno().product.fabric.puno = true;
        } else if (type == 'main') {
            this.item.product.fabric = fabric;
        }

    };
    this.setPattern = function (id, name, type) {
        var stripe = {};
        stripe.id = id;
        stripe.name = name;
        if (type == 'tira') {
            this.getItemTira().product.stripe = stripe;
        } else if (type == 'cuello') {
            this.getItemCuello().product.stripe = stripe;
        } else if (type == 'puno') {
            this.getItemPuno().product.stripe = stripe;
        } else if (type == 'main') {
            this.item.product.stripe = stripe;
        }
    };

    this.setPatternCombination = function (id, name, type) {
        var stripeCombination = {};
        stripeCombination.id = id;
        stripeCombination.name = name;
        if (type == 'tira') {
            this.getItemTira().product.stripeCombination = stripeCombination;
        } else if (type == 'cuello') {
            this.getItemCuello().product.stripeCombination = stripeCombination;
        } else if (type == 'puno') {
            this.getItemPuno().product.stripeCombination = stripeCombination;
        } else if (type == 'main') {
            this.item.product.stripeCombination = stripeCombination;
        }
    };

    this.setProduct = function (id, name, type) {
        if (type == 'tira') {
            this.getItemTira().product.id = id;
        } else if (type == 'cuello') {
            this.getItemCuello().product.id = id;
        } else if (type == 'puno') {
            this.getItemPuno().product.id = id;
        } else if (type == 'main') {
            this.item.product.id = id;
        }
    };


    this.addItem = function () {

        if (this.itemPuno != null) {
            this.getItemPuno().product.color = this.item.product.color;
            this.item.accesories.push(this.getItemPuno());
        }
        if (this.itemCuello != null) {
            this.getItemCuello().product.color = this.item.product.color;
            this.item.accesories.push(this.getItemCuello());
        }
        if (this.itemTira != null) {
            this.getItemTira().product.color = this.item.product.color;
            this.item.accesories.push(this.getItemTira());
        }

        var item = this.item;
        this.order.items.push(item);
        this.item = new OrderItem();
        this.itemTira = null;
        this.itemCuello = null;
        this.itemPuno = null;
        return item;
    };
    //  this.loadOrder and this.loadOrderItem reactorizarlos para que sean setters desde el controller.
    this.loadOrderItem = function (row) {
        var product = $(row).find(".item-main.product-cell");
        var data = $(product).data();

        if (typeof data != 'undefined') {
            this._loadItemData(data, "main");
        }

        product = $(row).find(".item-cuello.product-cell");
        if (typeof product != 'undefined') {
            data = $(product).data();
            if (typeof data != 'undefined') {
                this._loadItemData(data, "cuello");
            }
        }

        product = $(row).find(".item-tira.product-cell");
        if (typeof product != 'undefined') {
            data = $(product).data();
            if (typeof data != 'undefined') {
                this._loadItemData(data, "tira");
            }
        }

        product = $(row).find(".item-puno.product-cell");
        if (typeof product != 'undefined') {
            data = $(product).data();
            if (typeof data != 'undefined') {
                this._loadItemData(data, "puno");
            }
        }

        this.addItem();

    };

    this.getOrderItemByIndex = function (index) {
        return this.order.items[index];
    }
    this.editItemQuantitiesInOrder = function (index) {
        item = this.order.items[index];
        var value = $("#item-main-amount").val();
        if (item.quantity != value && isValidNumber(value) && value > 0) {
            item.quantity = $("#item-main-amount").val();
        }
        value = $("#item-puno-amount").val();
        if (item.quantity != value && isValidNumber(value) && value > 0) {
            item.getPunoItem().quantity = $("#item-puno-amount").val();
        }
        value = $("#item-cuello-amount").val();
        if (item.quantity != value && isValidNumber(value) && value > 0) {
            item.getCuelloItem().quantity = $("#item-cuello-amount").val();
        }
        value = $("#item-tira-amount").val();
        if (item.quantity != value && isValidNumber(value) && value > 0) {
            item.getTiraItem().quantity = $("#item-tira-amount").val();
        }

        return item;
    };
    this.deleteItem = function (index) {
        var items = [];
        for (var i = 0; i < this.order.items.length; i++) {
            if (index != i) {
                var item = this.order.items[i];
                items.push(item);
            }
        }
        ;
        this.order.items = items;
    };

    this._loadItemData = function (data, type) {
        this.setItemQuantity(data.quantity, type)
        this.setColor(data.colorId, data.colorName);
        this.setItemId(data.itemId, type);

        this.setProduct(data.productId, data.productName, type);
        this.setFabric(data.fabricId, data.fabricCode, data.fabricName, type);
        if (data.isStripe) {
            this.setPattern(data.stripeId, data.stripeName, type);
            this.setPatternCombination(data.combinationId, data.combinationName, type);
        }
    };


    this.get = function () {
        return this.order;
    };
}
function OrderItemAdjustment() {
    this.orderItemId = 0;
    this.orderId = 0;
    this.quantity = 0;
    this.operation = "";

    this.setOrderItemAdjustment = function (orderId, orderItemId, quantity, operation) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.operation = operation;
    }
}
function OrderEditor() {
    this.adjustments = [];
    this.addDeleteAdjustment = function (orderId, orderItemId) {
        var adjustment = new OrderItemAdjustment();
        adjustment.orderId = orderId;
        adjustment.orderItemId = orderItemId;
        adjustment.operation = "DELETE";
        this.adjustments.push(adjustment);
    };
    this.addEditAdjustment = function (orderId, orderItemId, item) {

        var itemIdAux = 0;
        var value = $("#item-puno-amount").val();
        var punoItem = item.getPunoItem();
        if (punoItem != null && punoItem.quantity != value && isValidNumber(value) && value > 0) {
            adjustment = new OrderItemAdjustment();
            itemIdAux = $("#row_" + orderItemId).data("puno-item-id");
            adjustment.setOrderItemAdjustment(orderId, itemIdAux, value, "EDIT");
            this.adjustments.push(adjustment);
        }
        value = $("#item-cuello-amount").val();
        var cuelloItem = item.getCuelloItem();
        if (cuelloItem != null && cuelloItem.quantity != value && isValidNumber(value) && value > 0) {
            var adjustment = new OrderItemAdjustment();
            itemIdAux = $("#row_" + orderItemId).data("cuello-item-id");
            adjustment.setOrderItemAdjustment(orderId, itemIdAux, value, "EDIT");
            this.adjustments.push(adjustment);
        }
        value = $("#item-tira-amount").val();
        var tiraItem = item.getTiraItem();
        if (tiraItem != null && tiraItem.quantity != value && isValidNumber(value) && value > 0) {
            var adjustment = new OrderItemAdjustment();
            itemIdAux = $("#row_" + orderItemId).data("tira-item-id");
            adjustment.setOrderItemAdjustment(orderId, itemIdAux, value, "EDIT");
            this.adjustments.push(adjustment);
        }

        value = $("#item-main-amount").val();

        if (item.quantity != value && isValidNumber(value) && value > 0) {
            var adjustment = new OrderItemAdjustment();
            adjustment.setOrderItemAdjustment(orderId, orderItemId, value, "EDIT");
            this.adjustments.push(adjustment);
        }

    };
}