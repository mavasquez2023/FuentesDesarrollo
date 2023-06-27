// JavaScript Document
$(document).ready(function() {
			setTimeout(function() {
				// Slide
				$('#menucp1 > li > a.expanded + ul').slideToggle(1);
				$('#menucp1 > li > a').click(function() {
					$(this).toggleClass('expanded').toggleClass('collapsed').parent().find('> ul').slideToggle(1);
				});
				/* $('#menucp1 .expand_all').click(function() {
					$('#menucp1 > li > a.collapsed').addClass('expanded').removeClass('collapsed').parent().find('> ul').slideDown(1);
				});
				$('#menucp1 .collapse_all').click(function() {
					$('#menucp1 > li > a.expanded').addClass('collapsed').removeClass('expanded').parent().find('> ul').slideUp(1);
				}); */
			}, 1);
		});