$('#confirmation-dialog').on('show.bs.modal', function (event) {

    var button = $(event.relatedTarget);

    var idExpense = button.data('id');
    var expenseDescription = button.data('description');

    var modal = $(this);
    var form = modal.find('form');
    var action = form.data('base-url');
    if (!action.endsWith('/')) {
        action += '/';
    }
    form.attr('action', action + idExpense);

    modal.find('.modal-body span').html('Are you sure you want to delete the expense: <strong>' + expenseDescription + '</strong>?');
});