import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class UserManager {

    private final Map<String, List<String>> userPermissions = new ConcurrentHashMap<>();

    public void addPermission(String userId, String permission) {
        if (userId == null || permission == null) {
            return;
        }
        userPermissions.computeIfAbsent(userId, k -> Collections.synchronizedList(new ArrayList<>()));
        List<String> permissions = userPermissions.get(userId);
        synchronized (permissions) {
            if (!permissions.contains(permission)) {
                permissions.add(permission);
            }
        }
    }

    public boolean hasPermission(String userId, String permission) {
        if (userId == null || permission == null) {
            return false;
        }
        List<String> permissions = userPermissions.get(userId);
        return permissions != null && permissions.contains(permission);
    }

    public void removePermission(String userId, String permission) {
        if (userId == null || permission == null) {
            return;
        }
        List<String> permissions = userPermissions.get(userId);
        if (permissions != null) {
            synchronized (permissions) {
                permissions.remove(permission);
            }
        }
    }

    public void clearPermissions(String userId) {
        if (userId == null) {
            return;
        }
        userPermissions.remove(userId);
    }

    public Set<String> getAllPermissions() {
        return userPermissions.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}