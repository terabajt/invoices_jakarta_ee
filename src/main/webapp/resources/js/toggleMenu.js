function toggleMenu() {
    const sidebar = document.querySelector('.sidebar');
    const content = document.querySelector('.content');

    sidebar.classList.toggle('hidden');

    if (window.innerWidth <= 768) {
        sidebar.classList.toggle('active');
    } else {
        content.classList.toggle('expanded');
    }
}

function checkScreenSize() {
    const sidebar = document.querySelector('.sidebar');
    const content = document.querySelector('.content');

    if (window.innerWidth <= 768) {
        sidebar.classList.add('hidden');
        content.classList.remove('expanded');
    } else {
        sidebar.classList.remove('hidden');
    }
}

window.addEventListener('load', checkScreenSize);
window.addEventListener('resize', checkScreenSize);
