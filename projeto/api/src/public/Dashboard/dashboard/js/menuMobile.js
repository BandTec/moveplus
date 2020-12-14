function menuActive() {
  const btnMenu = document.querySelector('.icon-menuHamburguer img');
  const ulMenu = document.getElementById('menuHidden');

  function openMenu() {
    ulMenu.classList.toggle('menu-hidden');
  }

  btnMenu.addEventListener('click', openMenu);
}

menuActive();
