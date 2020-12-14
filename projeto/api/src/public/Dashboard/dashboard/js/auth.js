function valida() {
  if (sessionStorage.status == undefined) {
    window.location = '../login/index.html';
  }
}

function logout() {
  sessionStorage.removeItem('status');
  window.location = '../login/index.html';
}

window.onload = valida();
