
var output_text;

function showCalendar(id, idButton, formato, showMore, mes, anno) 
{
    var el = document.getElementById(id);
    output_text = id;
    // first-time call, create the calendar.
    var cal = new Calendar(1, null, selected, closeHandler, showMore);
    // uncomment the following line to hide the week numbers
   cal.weekNumbers = false;

    _dynarch_popupCalendar = cal;                  // remember it in the global var
    cal.setRange(2000, 2070);        // min/max year allowed.
    cal.create();

	var fechaToShow = el.value;
	if (fechaToShow == '')
		fechaToShow = '01-' + mes + '-' + anno;
  _dynarch_popupCalendar.setDateFormat(formato);    // set the specified date format
  _dynarch_popupCalendar.parseDate(fechaToShow, formato);      // try to parse the text in field
  _dynarch_popupCalendar.sel = el;                 // inform it what input field we use

  // the reference element that we pass to showAtElement is the button that
  // triggers the calendar.  In this example we align the calendar bottom-right
  // to the button.
  _dynarch_popupCalendar.showAtElement(document.getElementById(idButton));  // show the calendar

  return false;
}

function closeHandler(cal) {
  cal.hide();                        // hide the calendar
  cal.destroy();
  _dynarch_popupCalendar = null;
}


function selected(cal, date) {
        if (cal.dateClicked) {
                // OK, a date was clicked, redirect to /yyyy/mm/dd/index.php
                var y = cal.date.getFullYear();
                var m = cal.date.getMonth()+1; // integer, 0..11
                var d = cal.date.getDate(); // integer, 1..31
                if (d<10) {
                        d="0"+d;
                }
                if (m<10) {
                        m="0"+m;
                }
                
                document.getElementById(output_text).value=String(d)+"-"+String(m)+"-"+String(y);
                cal.callCloseHandler();
        }   
}

