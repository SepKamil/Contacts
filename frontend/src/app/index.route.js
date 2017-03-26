(function() {
  'use strict';

  angular
    .module('frontend')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('home', {
        url: '/',
        templateUrl: 'app/main/main.html',
        controller: 'MainController',
        controllerAs: 'main'
      })
	  .state('contacts', {
        url: '/contacts',
        templateUrl: 'app/contacts/contacts.html',
        controller: 'ContactsController',
        controllerAs: 'main'
      });

    $urlRouterProvider.otherwise('/');
  }

})();
