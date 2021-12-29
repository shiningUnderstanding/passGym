/**
 *
 */
function menuClick() {
  let $menuObj = $(".link");
  $menuObj.click(function () {
    let menuHref = $(this).attr("href");
    let ajaxUrl = "";
    let ajaxMethod = "";
    switch (menuHref) {
      case "login.jsp":
        ajaxUrl = menuHref;
        ajaxMethod = "get";
        $("section").empty();
        $("section").load(ajaxUrl, function (responseText, textStatus, jqXHR) {
          if (jqXHR.status != 200) {
            alert("응답실패:" + jqXHR.status);
          }
        });
        return false;

      case "logout":
        ajaxUrl = menuHref;
        $.ajax({
          url: ajaxUrl,
          success: function () {
            location.href = "./index.jsp";
          },
          error: function (xhr) {
            alert("응답실패: " + xhr.status);
          },
        });
        return false;
    }
  });
}
