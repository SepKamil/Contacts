(function() {
  'use strict';

  angular
    .module('frontend')
    .controller('ContactsController', ContactsController);

  /** @ngInject */
  function ContactsController($timeout, webDevTec, toastr, $http) {
    var vm = this;
	vm.contacts = [];
	vm.currentUser = {};
	vm.newContact = {id:"", fname:"", lname:"", phone:"", birthday:"", location:"", notes:"", user:""};
	vm.getContacts = function(){
		$http.get("/api/contacts").then(function(response){
			vm.contacts = response.data;
		});
	
	}
  vm.getContacts();
  
  vm.deleteContact = function(contact){
	$http.delete("/api/contacts/"+contact.id).then(function(){
		var i = vm.contacts.indexOf(contact);
		vm.contacts.splice(i,1);
	});
  }
  
  vm.getCurrentUser = function() {
	$http.get("/api/currentuser").then(function(response) {
		vm.currentUser = response.data;
		vm.newContact.user = vm.currentUser;
		});
	}
	
	vm.getCurrentUser();
  
  vm.addContact = function(){
	$http.post("/api/contacts", vm.newContact).then(function(){
		vm.contacts.push(vm.newContact)
	});
  }
  
  vm.editContact = function(contact){
	$http.put("/api/contacts/"+contact.login, vm.newContact).then(function(){
		var x = vm.contacts.indexOf(contact);
		vm.contacts[x]=vm.newContact;	
	});
  }
  
}})();