public import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {

    private Map<String, List<String>> userPermissions = new HashMap<>();

    public void addPermission(String userId, String permission) {
        List<String> permissions = userPermissions.get(userId);
        if (permissions == null) {
            permissions = new ArrayList<>();
            userPermissions.put(userId, permissions);
        }
        if (!permissions.contains(permission)) {
            permissions.add(permission);
        }
    }

    public boolean hasPermission(String userId, String permission) {
        List<String> permissions = userPermissions.get(userId);
        return permissions != null && permissions.contains(permission);
    }

    public void removePermission(String userId, String permission) {
        List<String> permissions = userPermissions.get(userId);
        if (permissions != null) {
            permissions.remove(permission);
        }
    }

    public void clearPermissions(String userId) {
        userPermissions.put(userId, new ArrayList<>());
    }

    public List<String> getAllPermissions() {
        List<String> allPermissions = new ArrayList<>();
        for (String userId : userPermissions.keySet()) {
            allPermissions.addAll(userPermissions.get(userId));
        }
        return allPermissions;
    }
} file {
    
}
