

function openNewWindow(url, name) {
    window.open(
            url,
            name,
            "top=0,left=0,resizable=yes,scrollbars=yes,status=yes,"
                    + "location=yes,toolbar=yes,"
                    + "width=800,height=600");
}


function openFullNewWindow(url, name) {
    window.open(
            url,
            name,
            "top=0,left=0,resizable=yes,scrollbars=yes,status=yes,"
                    + "location=yes,toolbar=yes,"
                    + "width=" + screen.availWidth + ","
                    + "height=" + screen.availHeight);
}

function trim(s) {
    var i, j, ch;
    
    for (i = 0; i < s.length; i++) {
        ch = s.charAt(i);
        
        if (ch != ' ') {
            break;
        }
    }

    for (j = s.length - 1; j >= 0; j--) {
        ch = s.charAt(j);
        
        if (ch != ' ') {
            break;
        }
    }

    if (i > j) {
        return "";
    }
    
    return s.substring(i, j + 1);
}

function validateRut(s) {
    var index, rut, dv;
    
    if (s.length <= 3) {
        return 1;
    }

    index = s.indexOf("-");

    if (index <= 0 || index != s.length - 2)  {
        return 2;
    }

    rut = s.substring(0, index);
    
    if (!isNumber(rut)) {
        return 3;
    }
    
    dv = s.charAt(index + 1).toUpperCase();
    
    if (getDV(rut) != dv) {
        return 4;
    }
    
    return 0;
}

function isNumber(s) {
    var nmatches, i, ch;
    
    nmatches = 0;

    for (i = 0; i < s.length; i++) {
        ch = s.charAt(i);

        if ('0' <= ch && ch <= '9') {
            nmatches++;
        } else {
            return false;
        }
    }

    return nmatches > 0;
}

function getDV(rut) {
    var sum, factor, i, rest, dv;

    sum = 0;
    factor  = 2;

    for (i = rut.length - 1; i >= 0; i--) {
        sum = sum + rut.charAt(i) * factor;
        
        if (factor == 7) {
            factor = 2;
        } else {
            factor++;
        }
    }

    rest = sum % 11;
  
    if (rest == 1) {
        dv = 'K';
    } else if (rest == 0) {
        dv = '0';
    } else {
        dv = (11 - rest) + "";
    }
    
    return dv;
}
