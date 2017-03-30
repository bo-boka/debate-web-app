<div class="modal fade" id="edit-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h3 class="modal-title" id="exampleModalLabel">Edit Debate</h3>
            </div>
            <div class="modal-body">
                <h3 id="edit-debate-id"></h3>
                <form class="form-horizontal" id="blogForm">
                    <hr>
                    <div class="form-group">
                        <label for="editDebateCategory" class="col-sm-2 control-label">Category</label>
                        <div class="col-sm-4">
                            <select name="edit-debate-category" class="form-control" id="edit-debate-category">
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category}">${category}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editDebateResolution" class="col-sm-2 control-label">Resolution</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="edit-debate-resolution" placeholder="Resolution">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editDebateDate" class="col-sm-2 control-label">Date</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="edit-date-picker" placeholder="Click to add date">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editDebateAffUser" class="col-sm-2 control-label">Affirmative User</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="edit-debate-aff-user" placeholder="Affirmative User">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editDebateNegUser" class="col-sm-2 control-label">Negative User</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="edit-debate-neg-user" placeholder="Negative User">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editDebateContent" class="col-sm-2 control-label">Content</label>
                        <div class="col-sm-11">
                            <textarea name="editDebateContent" id="edit-debate-content"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editDebateStatus" class="col-sm-2 control-label">Status</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="edit-debate-status" placeholder="Status">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editDebateProVotes" class="col-sm-2 control-label">Pro Votes</label>
                        <div class="col-sm-4">
                            <input type="number" class="form-control" id="edit-debate-pro-votes" placeholder="Pro Votes">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editDebateConVotes" class="col-sm-2 control-label">Con Votes</label>
                        <div class="col-sm-4">
                            <input type="number" class="form-control" id="edit-debate-con-votes" placeholder="Con Votes">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isPublished" class="col-sm-4 control-label">Publish?</label>
                        <div class="col-sm-4">
                            <input type="radio" name="publishDebate" value="false">No
                            <input type="radio" name="publishDebate" value="true">Yes
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <!--<button type="submit" class="btn btn-secondary" id="delete-debate">Delete</button>-->
                <button type="submit" class="btn btn-lg btn-primary" id="edit-debate">Save changes</button>
                <div id="validationDebateEditErrors" class="alert alert-danger"
                    style="display:none">
                </div>
            </div>
        </div>
    </div>
</div>