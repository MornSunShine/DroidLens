package doridlens.metrics;

import doridlens.entity.Entity;

/**
 * Author: MaoMorn
 * Date: 2020/1/2
 * Time: 9:30
 * Description:
 */
public class IsStatic extends UnaryMetric<Boolean> {

    private IsStatic(Entity entity, boolean value) {
        this.value = value;
        this.entity = entity;
        this.name = "is_static";
    }

    public static IsStatic createIsStatic(Entity entity, boolean value) {
        IsStatic isStatic = new IsStatic(entity, value);
        isStatic.updateEntity();
        return isStatic;
    }
}
