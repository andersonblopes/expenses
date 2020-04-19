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

$(function () {
    $('[rel="tooltip"]').tooltip();
    $('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});

    $('.js-update-status').on('click', function (event) {
        event.preventDefault();

        var btnReceive = $(event.currentTarget);
        var urlReceive = btnReceive.attr('href');


        var response = $.ajax({
            url: urlReceive,
            type: 'PUT'
        });

        response.done(function (e) {
            var idExpense = btnReceive.data('id');
            $('[data-role=' + idExpense + ']').html('<span class="label label-success">' + e + '</span>');
            btnReceive.hide();
        });

        response.fail(function (e) {
            console.log(e);
            alert('Error receive expense');
        });

    });

});