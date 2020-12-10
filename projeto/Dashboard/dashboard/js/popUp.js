// PopUp das notificações

function myFunction() {
  var popup = document.getElementById('myPopup');
  popup.classList.toggle('show');
}

const popover = document.querySelector('.pop');
const activePop = document.querySelector('.active-pop');

function activePopover() {
  popover.classList.toggle('popBlock');
}

activePop.addEventListener('click', activePopover);

// PopUp do perfil

function profile() {
  const profileActive = document.querySelector('.profile-active');
  const infoProfile = document.querySelector('#info-profile');
  const profile = document.querySelector('.profile');
  const profileTxt = document.querySelector('.muda');
  const upProfile = document.querySelector('#up-profile');

  function activeProfile() {
    profileActive.classList.toggle('popBlock');
    profile.classList.toggle('profile-teste');
    profileTxt.classList.toggle('profileTxta');
    infoProfile.classList.toggle('none');
    upProfile.classList.toggle('popBlock');
  }

  infoProfile.addEventListener('click', activeProfile);
  upProfile.addEventListener('click', activeProfile);
}
profile();
