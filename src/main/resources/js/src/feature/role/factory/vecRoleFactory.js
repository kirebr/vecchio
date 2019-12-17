vecchioApp.factory('vecRoleFactory', [
	'$uibModal','vecRoleResource', 'Notification','$state',
		function($uibModal,vecRoleResource, Notification, $state) {
			return {
				delete : function(vecRole) {
					var modalInstance = this.modalDelete(vecRole);
					modalInstance.result.then(function(removeInstance) {
						deletedMessage = 'The ' + vecRole.name +' was deleted.';
						
						vecRoleResource
						.delete({
							id : vecRole.id
						},function() {
							Notification.error(deletedMessage);
							$state.go('console.organization.role',{}, {reload: true});
						});
					}, function() {
						// Selected NO
					});
				}, 			
				save: function(vecRole) {
					if (vecRole.id > 0 ) {
						var updateMessage = 'The ' + vecRole.name +' was saved.';
						vecRole.$update(function() {
							Notification.info(updateMessage);
							$state.go('console.organization.role');						
						});
					} else {
						var saveMessage = 'The ' + vecRole.name +' was updated.';
						delete vecRole.id;
						vecRoleResource.save(vecRole, function(response){
							Notification.info(saveMessage);
							$state.go('console.organization.role');
						});
					}	
				},
				modalDelete: function (vecRole) {
					var $ctrl = this;
					return $uibModal.open({
						animation: true
						, ariaLabelledBy: 'modal-title'
						, ariaDescribedBy: 'modal-body'
						, templateUrl: 'template/user/user-delete.html'
						, controller: 'VecModalDeleteUserCtrl'
						, controllerAs: '$ctrl'
						, size: null
						, appendTo: undefined
						, resolve: {
							title: function () {
								return vecRole.name;
							}
						}
					});
				}					
			}
		} ]);
