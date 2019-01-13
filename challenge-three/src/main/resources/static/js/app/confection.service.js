'use strict';

function ConfectionService($http, keyService) {
    this.$http = $http;
    this.keyService = keyService;
}

ConfectionService.prototype.setup = function (scope, url, init, load, prepareToSave) {
    var _this = this;
    var key = this.keyService.key;
    this.keyService.key = null;
    init();
    if (key) {
        this.$http.get(url + key).then(function (response) {
            load(response.data);
        }, errorCallback);
    }
    scope.save = function () {
        var entity = prepareToSave();
        if (key) {
            _this.$http.post(url + key, entity).then(function () {
                alert("salvo!");
            }, errorCallback);
        }
        else {
            _this.$http.put(url, entity).then(function () {
                alert("salvo!");
                init();
            }, errorCallback);
        }
    };
};

main.service('$confectionService', ['$http', "$keyService", ConfectionService]);