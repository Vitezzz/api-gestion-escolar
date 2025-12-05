

// Configuración de la API
const API_BASE_URL = '/api';

// Obtener token del sessionStorage
function getToken() {
    return sessionStorage.getItem('token');
}

// Verificar si el usuario está autenticado
function isAuthenticated() {
    return getToken() !== null;
}

// Verificar autenticación y redirigir si es necesario
function checkAuth() {
    if (!isAuthenticated()) {
        window.location.href = '/login';
        return false;
    }
    return true;
}

// Cerrar sesión
function logout() {
    sessionStorage.clear();
    window.location.href = '/login';
}

// Obtener headers para peticiones con autenticación
function getAuthHeaders() {
    return {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${getToken()}`
    };
}

// Realizar petición GET con autenticación
async function apiGet(endpoint) {
    try {
        const response = await fetch(`${API_BASE_URL}${endpoint}`, {
            method: 'GET',
            headers: getAuthHeaders()
        });
        
        if (response.status === 401 || response.status === 403) {
            logout();
            return null;
        }
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        return await response.json();
    } catch (error) {
        console.error('Error en GET:', error);
        showError('Error al cargar los datos');
        return null;
    }
}

// Realizar petición POST con autenticación
async function apiPost(endpoint, data) {
    try {
        const response = await fetch(`${API_BASE_URL}${endpoint}`, {
            method: 'POST',
            headers: getAuthHeaders(),
            body: JSON.stringify(data)
        });
        
        if (response.status === 401 || response.status === 403) {
            logout();
            return null;
        }
        
        const result = await response.json();
        
        // Si la respuesta tiene success: false, mostrar error
        if (result.success === false) {
            showError(result.message || 'Error al guardar los datos');
            return null;
        }
        
        return result;
    } catch (error) {
        console.error('Error en POST:', error);
        showError('Error al guardar los datos');
        return null;
    }
}

// Realizar petición PUT con autenticación
async function apiPut(endpoint, data) {
    try {
        const response = await fetch(`${API_BASE_URL}${endpoint}`, {
            method: 'PUT',
            headers: getAuthHeaders(),
            body: JSON.stringify(data)
        });
        
        if (response.status === 401 || response.status === 403) {
            logout();
            return null;
        }
        
        const result = await response.json();
        
        // Si la respuesta tiene success: false, mostrar error
        if (result.success === false) {
            showError(result.message || 'Error al actualizar los datos');
            return null;
        }
        
        return result;
    } catch (error) {
        console.error('Error en PUT:', error);
        showError('Error al actualizar los datos');
        return null;
    }
}

// Realizar petición DELETE con autenticación
async function apiDelete(endpoint) {
    try {
        const response = await fetch(`${API_BASE_URL}${endpoint}`, {
            method: 'DELETE',
            headers: getAuthHeaders()
        });
        
        if (response.status === 401 || response.status === 403) {
            logout();
            return null;
        }
        
        return await response.json();
    } catch (error) {
        console.error('Error en DELETE:', error);
        showError('Error al eliminar el registro');
        return null;
    }
}

// Mostrar mensaje de error
function showError(message) {
    const alertHtml = `
        <div class="xp-alert xp-alert-error" style="animation: slideIn 0.3s;">
            <strong>⚠ Error:</strong> ${message}
        </div>
    `;
    showAlert(alertHtml, 5000);
}

// Mostrar mensaje de éxito
function showSuccess(message) {
    const alertHtml = `
        <div class="xp-alert xp-alert-success" style="animation: slideIn 0.3s;">
            <strong>✓ Éxito:</strong> ${message}
        </div>
    `;
    showAlert(alertHtml, 3000);
}

// Mostrar mensaje de información
function showInfo(message) {
    const alertHtml = `
        <div class="xp-alert xp-alert-info" style="animation: slideIn 0.3s;">
            <strong>ℹ Información:</strong> ${message}
        </div>
    `;
    showAlert(alertHtml, 4000);
}

// Mostrar alerta temporal
function showAlert(html, duration = 3000) {
    const container = document.getElementById('alertContainer');
    if (!container) {
        const newContainer = document.createElement('div');
        newContainer.id = 'alertContainer';
        newContainer.style.cssText = 'position: fixed; top: 50px; right: 20px; z-index: 9999; max-width: 400px;';
        document.body.appendChild(newContainer);
    }
    
    const alertContainer = document.getElementById('alertContainer');
    const alertDiv = document.createElement('div');
    alertDiv.innerHTML = html;
    alertContainer.appendChild(alertDiv.firstElementChild);
    
    setTimeout(() => {
        const alert = alertContainer.firstElementChild;
        if (alert) {
            alert.style.animation = 'slideOut 0.3s';
            setTimeout(() => alert.remove(), 300);
        }
    }, duration);
}

// Confirmar acción
function confirmAction(message, callback) {
    const overlay = document.createElement('div');
    overlay.className = 'xp-modal-overlay';
    overlay.innerHTML = `
        <div class="xp-window" style="width: 400px;">
            <div class="xp-titlebar">
                <div class="xp-titlebar-icon">⚠</div>
                <div class="xp-titlebar-text">Confirmar acción</div>
            </div>
            <div class="xp-window-content">
                <p style="margin-bottom: 16px;">${message}</p>
                <div class="flex justify-end gap-2">
                    <button class="xp-button" onclick="this.closest('.xp-modal-overlay').remove()">
                        Cancelar
                    </button>
                    <button class="xp-button xp-button-primary" id="confirmBtn">
                        Aceptar
                    </button>
                </div>
            </div>
        </div>
    `;
    
    document.body.appendChild(overlay);
    
    document.getElementById('confirmBtn').addEventListener('click', () => {
        overlay.remove();
        callback();
    });
}

// Formatear fecha
function formatDate(dateString) {
    if (!dateString) return '-';
    const date = new Date(dateString);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
}

// Formatear fecha y hora
function formatDateTime(dateString) {
    if (!dateString) return '-';
    const date = new Date(dateString);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    return `${day}/${month}/${year} ${hours}:${minutes}`;
}

// Convertir fecha a formato ISO para input date
function toInputDate(dateString) {
    if (!dateString) return '';
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

// Obtener badge de estado
function getStatusBadge(status) {
    const statusMap = {
        'ACTIVO': '<span class="status-badge status-activo">Activo</span>',
        'INACTIVO': '<span class="status-badge status-inactivo">Inactivo</span>',
        'ABIERTA': '<span class="status-badge status-abierta">Abierta</span>',
        'CERRADA': '<span class="status-badge status-cerrada">Cerrada</span>',
        'ACTIVA': '<span class="status-badge status-activo">Activa</span>',
        'PENDIENTE': '<span class="status-badge status-pendiente">Pendiente</span>'
    };
    return statusMap[status] || `<span class="status-badge">${status}</span>`;
}

// Actualizar reloj de la taskbar
function updateClock() {
    const now = new Date();
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const taskbarTime = document.getElementById('taskbarTime');
    if (taskbarTime) {
        taskbarTime.textContent = `${hours}:${minutes}`;
    }
}

// Cargar información del usuario
function loadUserInfo() {
    const username = sessionStorage.getItem('username');
    const roles = JSON.parse(sessionStorage.getItem('roles') || '[]');
    
    return {
        username: username || 'Usuario',
        roles: roles
    };
}

// Mostrar loader
function showLoader() {
    const loader = document.createElement('div');
    loader.id = 'globalLoader';
    loader.style.cssText = `
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background: rgba(0, 0, 0, 0.5);
        display: flex;
        align-items: center;
        justify-content: center;
        z-index: 9999;
    `;
    loader.innerHTML = `
        <div class="xp-window" style="width: 250px;">
            <div class="xp-window-content text-center">
                <div class="xp-loading" style="width: 32px; height: 32px; margin: 0 auto 12px;"></div>
                <p>Cargando...</p>
            </div>
        </div>
    `;
    document.body.appendChild(loader);
}

// Ocultar loader
function hideLoader() {
    const loader = document.getElementById('globalLoader');
    if (loader) {
        loader.remove();
    }
}

// Paginación simple
function paginate(items, page = 1, perPage = 10) {
    const offset = (page - 1) * perPage;
    const paginatedItems = items.slice(offset, offset + perPage);
    const totalPages = Math.ceil(items.length / perPage);
    
    return {
        page: page,
        perPage: perPage,
        total: items.length,
        totalPages: totalPages,
        data: paginatedItems
    };
}

// Crear controles de paginación
function createPaginationControls(totalPages, currentPage, onPageChange) {
    let html = '<div class="pagination">';
    
    if (currentPage > 1) {
        html += `<button class="xp-button" onclick="${onPageChange}(${currentPage - 1})">◄ Anterior</button>`;
    }
    
    for (let i = 1; i <= totalPages; i++) {
        const active = i === currentPage ? 'active' : '';
        html += `<button class="xp-button ${active}" onclick="${onPageChange}(${i})">${i}</button>`;
    }
    
    if (currentPage < totalPages) {
        html += `<button class="xp-button" onclick="${onPageChange}(${currentPage + 1})">Siguiente ►</button>`;
    }
    
    html += '</div>';
    return html;
}

// Filtrar tabla
function filterTable(searchTerm, data, fields) {
    if (!searchTerm) return data;
    
    const term = searchTerm.toLowerCase();
    return data.filter(item => {
        return fields.some(field => {
            const value = item[field];
            return value && String(value).toLowerCase().includes(term);
        });
    });
}

// Validar email
function isValidEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}

// Validar teléfono (formato mexicano)
function isValidPhone(phone) {
    const re = /^[0-9]{10}$/;
    return re.test(phone.replace(/\s/g, ''));
}

// Inicializar reloj y verificar autenticación al cargar
document.addEventListener('DOMContentLoaded', () => {
    updateClock();
    setInterval(updateClock, 1000);
    
    // Agregar estilos de animación
    const style = document.createElement('style');
    style.textContent = `
        @keyframes slideIn {
            from {
                transform: translateX(100%);
                opacity: 0;
            }
            to {
                transform: translateX(0);
                opacity: 1;
            }
        }
        
        @keyframes slideOut {
            from {
                transform: translateX(0);
                opacity: 1;
            }
            to {
                transform: translateX(100%);
                opacity: 0;
            }
        }
    `;
    document.head.appendChild(style);
});