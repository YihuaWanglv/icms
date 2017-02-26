$(function() {
  $("#header").load("/view/header.html", function(){
    if (TI_cookie.getCookie('username')) {
      $('.field-username').text(TI_cookie.getCookie('username'));
      $('.login-on').show();
      $('.login-off').hide();
    } else {
      $('.login-off').show();
      $('.login-on').hide();
    }
    if (TI_cookie.getCookie('isAdmin')) {
      var isAdmin = TI_cookie.getCookie('isAdmin');
      console.log(isAdmin);
      if (isAdmin && isAdmin=='1') {
        $('.is-admin').show();
      } else {
        $('.is-admin').hide();
      }
    } else {
      $('.is-admin').hide();
    }
  });
});