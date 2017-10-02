'use strict';

angular.module('bookStoreApp')
  .factory('bookService', function bookService($q, $http) {
    return {
    	add: function(book){
	        var defer = $q.defer();
	
	        $http.post('/BookStore/addBook',
	          book
	        ).success(function (data) {
	          defer.resolve(data);
	        }).error(function (data) {
	          defer.reject(data);
	        });
	
	        return defer.promise;
    	},
    	getAll: function(){
	        var defer = $q.defer();
	        $http.post('/BookStore/inventory'          
	        ).success(function (data) {
	          defer.resolve(data);
	        }).error(function (data) {
	          defer.reject(data);
	        });
	
	        return defer.promise;
    	},
    	getBook: function(data){
	        var defer = $q.defer();
	        $http.get('/BookStore/getBook?' + data
	        ).success(function (data) {
	          defer.resolve(data);
	        }).error(function (data) {
	          defer.reject(data);
	        });
	
	        return defer.promise;
    	},
    	deleteB: function(data){
	        var defer = $q.defer();
	        $http.get('/BookStore/deleteBook?' + data
	        ).success(function (data) {
	          defer.resolve(data);
	        }).error(function (data) {
	          defer.reject(data);
	        });
	
	        return defer.promise;
    	},
    	update: function(book){
	        var defer = $q.defer();
	
	        $http.post('/BookStore/updateBook',
	          book
	        ).success(function (data) {
	          defer.resolve(data);
	        }).error(function (data) {
	          defer.reject(data);
	        });
	
	        return defer.promise;
    	},
    	getCategory: function(){
	        var defer = $q.defer();
	        $http.post('/BookStore/getCategory'          
	        ).success(function (data) {
	          defer.resolve(data);
	        }).error(function (data) {
	          defer.reject(data);
	        });
	
	        return defer.promise;
    	},
    	getCategoryByLabel: function(data){
	        var defer = $q.defer();
	        $http.get('/BookStore/getCategoryByLabel?' + data          
	        ).success(function (data) {
	          defer.resolve(data);
	        }).error(function (data) {
	          defer.reject(data);
	        });
	
	        return defer.promise;
    	},
    	addItem: function(data){
	        var defer = $q.defer();
	        $http.get('/BookStore/addItem?' + data
	        ).success(function (data) {
	          defer.resolve(data);
	        }).error(function (data) {
	          defer.reject(data);
	        });
	
	        return defer.promise;
    	},
    	getAllItem: function(data){
	        var defer = $q.defer();
	        $http.post('/BookStore/getAllItem'
	        ).success(function (data) {
	          defer.resolve(data);
	        }).error(function (data) {
	          defer.reject(data);
	        });
	
	        return defer.promise;
    	},
    	getGift: function(data){
	        var defer = $q.defer();
	        $http.post('/BookStore/getGift'
	        ).success(function (data) {
	          defer.resolve(data);
	        }).error(function (data) {
	          defer.reject(data);
	        });
	
	        return defer.promise;
    	},
    	getNumberBookByCategory: function(data){
	        var defer = $q.defer();
	        $http.post('/BookStore/getNumberBookByCategory'
	        ).success(function (data) {
	          defer.resolve(data);
	        }).error(function (data) {
	          defer.reject(data);
	        });
	
	        return defer.promise;
    	},
    	
    	getAmountTotal: function(data){
	        var defer = $q.defer();
	        $http.post('/BookStore/getAmountTotal'
	        ).success(function (data) {
	          defer.resolve(data);
	        }).error(function (data) {
	          defer.reject(data);
	        });
	
	        return defer.promise;
    	}
    };
  });
