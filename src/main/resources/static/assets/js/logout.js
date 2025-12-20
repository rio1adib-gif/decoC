function logout() {
    // 1. Clear LocalStorage
    localStorage.clear();
    
    // 2. Clear Cookie (by setting expiry to past)
    document.cookie = "jwt_token=; path=/; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
    
    // 3. Redirect
    window.location.href = "/";
}