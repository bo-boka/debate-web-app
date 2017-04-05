<div class="modal fade" id="edit-rebuttal-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h3 class="modal-title" id="exampleModalLabel">Edit Rebuttal</h3>
            </div>
            <div class="modal-body">
                <input type=hidden id="edit-rebuttal-id"/>
                <form class="form-horizontal" id="rebuttal-form">
                    <hr>                    
                    <div class="form-group">
                        <div class="col-sm-11">
                            <textarea name="editRebuttalContent" id="edit-rebuttal-content"></textarea>
                        </div>
                    </div>                    
                </form>
            </div>
            <div class="modal-footer">                
                <button type="submit" class="btn btn-lg btn-primary" id="edit-rebuttal">Save changes</button>
                <div id="validationRebuttalEditErrors" class="alert alert-danger"
                    style="display:none">
                </div>
            </div>
        </div>
    </div>
</div>