<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

 <header class="header"><a class="header__item header__item--logo" href="#"><img src="img/logo.svg"/>
                    <div class="header__text oculto-xs block-md"><span>Sucursal</span> Virtual</div></a>
 
                <div class="header__item header__item--user oculto-xs flex-xl align-middle-xl">
                    <div class="header-user">
                        <div class="header-user__info">                                               
                        
                            <div class="header-user__text">
                                <div class="header-user__hi">Bienvenido</div>
                                <div class="header-user__name">${nombreUsuario }</div>
                            </div>
                            
                            <div class="header-user__icon fln-abajo-light"></div>
                            
                            <div class="header-user__options">
                                <a class="header-user__option" href="exit.do">
                                    <div class="header-user__title">Cerrar</div>
                                    <div class="header-user__ico"><svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" viewBox="0 0 80 80"><title>  salir</title><path d="M73 38.7c-0.1-0.4-0.2-0.8-0.4-1.1L58.3 17.8c-0.4-0.7-1.3-0.9-1.9-0.5 -0.2 0.1-0.3 0.2-0.4 0.4 -0.6 1-0.6 2.2 0 3.2L67.5 37H38.3c-0.9 0-1.7 1-1.7 2.3s0.8 2.3 1.7 2.3H68L55.9 59.1c-0.6 1-0.6 2.2 0.1 3.2 0.3 0.4 0.7 0.6 1.1 0.6 0.5 0 1-0.3 1.2-0.7l14.4-21C73.2 40.4 73.3 39.5 73 38.7z"/><path d="M35.8 71H15c-2.9-0.3-5.1-2.9-4.9-5.8V14.9C9.9 11.9 12 9.3 15 9h20.8c1-0.1 1.7-1 1.7-2 0.1-1-0.7-1.9-1.7-2H15c-4.5 0-8.2 4.4-8.2 9.9v50.3c0 5.4 3.7 9.9 8.2 9.9h20.8c1.1 0 2-0.9 2-2S36.9 71 35.8 71z"/></svg></div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="header__item header__item--burger oculto-xl flex-xs align-middle-xs"><a href="#menu-responsive" onclick="MENU_RESPONSIVE.toggle(); return false;"><svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" viewBox="0 0 35 35"><rect x="2.5" y="24.1" width="30" height="1.9"/><rect x="2.5" y="9.1" width="30" height="1.9"/><rect x="2.5" y="16.6" width="30" height="1.9"/></svg></a></div>
            </header>