package net.azisaba.library.common.permission;

import org.jetbrains.annotations.NotNull;

public interface PermissionHolder {
    /**
     * Checks if the holder has the provided permission.
     * @param permission the permission
     * @return true if holder has permission, false otherwise
     */
    boolean hasPermission(@NotNull String permission);

    @NotNull
    default PermissionState getPermissionState(@NotNull String permission) {
        if (hasPermission(permission)) {
            return PermissionState.TRUE;
        } else {
            return PermissionState.FALSE;
        }
    }
}
