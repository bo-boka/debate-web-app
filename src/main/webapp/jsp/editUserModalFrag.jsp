<div class="modal fade" id="edit-user-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h3 class="modal-title" id="editUserModalLabel">Edit Account Information</h3>
            </div>
            <div class="modal-body">
                <input type=hidden id="edit-user-id"/>
                <form action="register" method="POST" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="edit-first-name" class="col-md-4 control-label">First Name:</label>
                            <div class="col-md-8">
                                <input id="edit-first-name" name="editFirstName" type="text" class="form-control" placeholder="first name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit-last-name" class="col-md-4 control-label">Last Name:</label>
                            <div class="col-md-8">
                                <input id="edit-last-name" name="editLastName" type="text" class="form-control" placeholder="last name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit-email" class="col-md-4 control-label">Email:</label>
                            <div class="col-md-8">
                                <input id="edit-email" name="editEmail" type="text" class="form-control" placeholder="Email"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit-username" class="col-md-4 control-label">Username:</label>
                            <div class="col-md-8">
                                <input id="edit-username" name="editUsername" type="text" class="form-control" placeholder="username"/>
                            </div>
                        </div>
<!--                        <div class="form-group">
                            <label for="edit-password" class="col-md-4 control-label">Password:</label>
                            <div class="col-md-8">
                                <input id="edit-password" name="editPassword" type="password" class="form-control" placeholder="password"/>
                            </div>
                        </div>-->
                    </form>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-lg btn-primary" id="edit-user">Save changes</button>
                <div id="validationEditUserErrors" class="alert alert-danger" style="display:none"></div>
            </div>
        </div>
    </div>
</div>