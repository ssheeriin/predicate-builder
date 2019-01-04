package com.sherinstephen.expressionbuilder.temp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {
       
    }

    static String[] types = { "read", "write", "none" };

    static List<FieldPermission> getFieldPermissions() {

        List<FieldPermission> fpList = new ArrayList<>();
        IntStream.range(1, 4).forEach((i) -> {
            FieldPermission p = new FieldPermission();

            List<Field> fields = new ArrayList<>();
            List<TableColumnPermission> tabColPerms = new ArrayList<>();
            IntStream.range(1, 3).forEach(j -> {
                Field f = new Field("fp" + i + "f" + j,
                    Arrays.asList(new TableColumn("tc" + i), new TableColumn("tc" + j)),
                    i % 2 == 0 ? "table" : "field");
                fields.add(f);

                tabColPerms.add(new TableColumnPermission(f, new TableColumn(f.id)));
            });

            p.fields = fields;
            p.setRoleNames(Arrays.asList("E", "EM"));
            p.setType(types[(int) (Math.random() * 10) % 3]);
            p.setTableColumnPermissions(tabColPerms);

            fpList.add(p);
        });

        return fpList;
    }

    static Map<String, TableColumnPermissionSet> testTableColumnPermissionSets(String objAssignee) {
        // exploit locality of reference by "caching" the last looked up
        // permission set.

        Map<String, TableColumnPermissionSet> columnPermissionsPerTable = new HashMap<String, TableColumnPermissionSet>();

        List fieldPerms = getFieldPermissions();

        for (int i = 0, n = fieldPerms.size(); i < n; ++i) {
            FieldPermission fieldPerm = (FieldPermission) fieldPerms.get(i);

            boolean isUserInRoles = isUserInRoles(objAssignee, fieldPerm.getRoleNames());

            List<Field> fields = fieldPerm.getFields();

            for (int j = 0, m = fields.size(); j < m; ++j) {

                Field field = fields.get(j);

                if (field.getType().equals("table")) {

                    if (!columnPermissionsPerTable.containsKey(field.getId())) {
                        columnPermissionsPerTable.put(field.getId(), new TableColumnPermissionSet());
                    }
                    if (isUserInRoles) {
                        if (FieldPermission.TYPE_NONE.equals(fieldPerm.getType())) {
                            columnPermissionsPerTable.get(field.getId()).setTableNoAccess();
                        } else if (FieldPermission.TYPE_READ.equals(fieldPerm.getType())) {
                            columnPermissionsPerTable.get(field.getId()).setTableReadable(field);
                        } else if (FieldPermission.TYPE_WRITE.equals(fieldPerm.getType())) {
                            columnPermissionsPerTable.get(field.getId()).setTableWriteable(field);
                        }
                    }
                }
            }

            List<TableColumnPermission> tableCols = fieldPerm.getTableColumnPermissions();
            for (int j = 0, m = tableCols.size(); j < m; ++j) {

                TableColumnPermission columnPermission = tableCols.get(j);
                Field tableField = columnPermission.getField();
                TableColumn column = columnPermission.getTableColumn();

                if (!columnPermissionsPerTable.containsKey(tableField.getId())) {
                    columnPermissionsPerTable.put(tableField.getId(), new TableColumnPermissionSet());
                }
                if (isUserInRoles) {
                    if (FieldPermission.TYPE_NONE.equals(fieldPerm.getType())) {
                        columnPermissionsPerTable.get(tableField.getId()).addNoAccessColumn(column.getId());
                    } else if (FieldPermission.TYPE_READ.equals(fieldPerm.getType())) {
                        columnPermissionsPerTable.get(tableField.getId()).addReadableOnlyColumn(column.getId());
                    } else if (FieldPermission.TYPE_WRITE.equals(fieldPerm.getType())) {
                        columnPermissionsPerTable.get(tableField.getId()).addWriteableColumn(column.getId());
                    }
                }
            }
        }

        return columnPermissionsPerTable;
    }

    static Map<String, List<String>> userRoles = new HashMap<>();

    static {
        userRoles.put("u1", Arrays.asList("E", "EM"));
        userRoles.put("u2", Arrays.asList("E", "EM+"));
        userRoles.put("u3", Arrays.asList("E", "EH"));
    }

    private static boolean isUserInRoles(String objAssignee, List<String> roleNames) {
        return userRoles.get(objAssignee).stream().anyMatch(roleNames::contains);
    }

}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class FieldPermission {
    public static String TYPE_NONE = "none";

    public static String TYPE_READ = "read";

    public static String TYPE_WRITE = "write";

    private List<TableColumnPermission> tableColumnPermissions;

    private String type;

    private String description;

    private List<String> roleNames;

    public List<Field> fields;

}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class TableColumnPermissionSet {

    private Set<String> _writeColumnSet = new HashSet<>();

    private Set<String> _readColumnSet = new HashSet<>();

    private boolean _isTableReadable = false;

    private boolean _isTableWriteable = false;

    public void setTableReadable(Field tableField) {

        _isTableReadable = true;
        for (Object tableColumnObject : tableField.getTableColumns()) {
            TableColumn tableColumn = (TableColumn) tableColumnObject;
            _readColumnSet.add(tableColumn.getId());
        }
    }

    public void setTableWriteable(Field tableField) {
        _isTableWriteable = true;
        _isTableReadable = true;
        for (Object tableColumnObject : tableField.getTableColumns()) {
            TableColumn tableColumn = (TableColumn) tableColumnObject;
            _writeColumnSet.add(tableColumn.getId());
            _readColumnSet.add(tableColumn.getId());
        }
    }

    public void setTableNoAccess() {
        _isTableReadable = false;
        _isTableWriteable = false;
        _writeColumnSet.clear();
        _readColumnSet.clear();
    }

    public void addNoAccessColumn(String tableColumnId) {
        _readColumnSet.remove(tableColumnId);
        _writeColumnSet.remove(tableColumnId);
    }

    public void addReadableOnlyColumn(String tableColumnId) {
        _readColumnSet.add(tableColumnId);
        _writeColumnSet.remove(tableColumnId);
    }

    public void addWriteableColumn(String tableColumnId) {
        _readColumnSet.add(tableColumnId);
        _writeColumnSet.add(tableColumnId);
    }

    public final boolean canRead(String tableColumnId) {
        return (_readColumnSet.contains(tableColumnId) && _isTableReadable);
    }

    public final boolean canWrite(String tableColumnId) {
        return (_writeColumnSet.contains(tableColumnId) && _isTableWriteable);
    }

    public final boolean canReadAny() {
        return (!_readColumnSet.isEmpty() && _isTableReadable);
    }

    public final boolean canWriteAny() {
        return (!_writeColumnSet.isEmpty() && _isTableWriteable);
    }
}

@AllArgsConstructor
@Getter
@Setter
class TableColumnPermission {
    Field field;

    TableColumn tableColumn;
}

@AllArgsConstructor
@Getter
@Setter
class TableColumn {

    private String id;
}

@AllArgsConstructor
@Getter
@Setter
class Field {
    String id;

    List<TableColumn> tableColumns;

    String type;

}
