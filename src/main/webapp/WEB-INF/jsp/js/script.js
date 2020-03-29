$(document).ready(function () {
  $('#save').on('click', function (event) {
    event.preventDefault();
    saveAjax();
  });

  $('#send').on('click', function (event) {
    event.preventDefault();
    sendAjax();
  });

  disableMail();
});

function saveAjax() {
  if ($("#mail").val().length < 6 || $("#url").val().length < 6) {
    printText("<span style='color:red'>Enter URL or Mail address!</span>");
    return;
  }

  disableButton('#save');
  printText("loading...");

  var dataString = 'url=' + $("#url").val() + '&mail=' + $("#mail").val();
  $.ajax({
    type: "POST",
    url: "index/saveAjax",
    data: dataString,
    success: function (data) {
      if (data != "Error") {
        $('#rssList').append("<a href='' onclick='removeRSS(" + data + ")'>[x]</a>&nbsp;")
                     .append($("#url").val()).append("<br>");
        printText("");
        disableMail();
      } else {
        printText("<span style='color:red'>" + data + "!</span>");
      }
      enableButton("#save");
    }
  });
}

function sendAjax() {
  if ($("#mail").val().length < 6) {
    printText("<span style='color:red'>Enter your Mail address!</span>");
    return;
  }

  disableButton('#send');
  printText("loading...");

  var dataString = 'mail=' + $("#mail").val();
  $.ajax({
    type: "POST",
    url: "index/sendAjax",
    data: dataString,
    success: function (data) {
      if (data != "Error") {
        printText(data);
      } else {
        printText("<span style='color:red'>" + data + "!</span>");
      }
      enableButton("#send");
    }
  });
}

function disableMail() {
  if ($('#mail').val() != "") {
    $('#mail').attr("disabled", true);
  }
}

function disableButton(buttonId) {
  $(buttonId).attr("disabled", true);
}

function enableButton(buttonId) {
  $(buttonId).removeAttr("disabled");
}

function printText(text) {
  $('#mailView').html(text);
}

function removeRSS(id) {
  $.ajax({
    type: "POST",
    url: "index/removeAjax",
    data: "id=" + Number(id)
  });
}
