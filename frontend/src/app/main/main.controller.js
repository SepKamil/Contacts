(function() {
  'use strict';

  angular
    .module('frontend')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController($timeout, webDevTec, toastr, $http) {
    var vm = this;
	vm.users = [];
	vm.newUser = {login:"", password:""};
	vm.getUsers = function(){
		$http.get("/api/users").then(function(response){
			vm.users = response.data;
		});
	
	}
  vm.getUsers();
  
  vm.deleteUser = function(user){
	$http.delete("/api/users/"+user.login).then(function(){
		var i = vm.users.indexOf(user);
		vm.users.splice(i,1);
	});
  }
  
  vm.addUser = function(){
	$http.post("/api/users", vm.newUser).then(function(){
		vm.users.push(vm.newUser)
	});
  }
  
  vm.editUser = function(user){
	$http.put("/api/users/"+user.login, vm.newUser).then(function(){
		var x = vm.users.indexOf(user);
		vm.users[x]=vm.newUser;	
	});
  }
  
}})();