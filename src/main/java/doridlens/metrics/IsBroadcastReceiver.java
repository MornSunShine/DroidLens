package doridlens.metrics;

import doridlens.entity.PaprikaClass;

/**
 * Author: MaoMorn
 * Date: 2020/1/2
 * Time: 9:26
 * Description:
 */
public class IsBroadcastReceiver extends UnaryMetric<Boolean> {

    private IsBroadcastReceiver(PaprikaClass entity, boolean value) {
        this.value = value;
        this.entity = entity;
        this.name = "is_broadcast_receiver";
    }

    public static IsBroadcastReceiver createIsBroadcastReceiver(PaprikaClass entity, boolean value) {
        IsBroadcastReceiver isBroadcastReceiver= new IsBroadcastReceiver(entity, value);
        isBroadcastReceiver.updateEntity();
        return isBroadcastReceiver;
    }
}
