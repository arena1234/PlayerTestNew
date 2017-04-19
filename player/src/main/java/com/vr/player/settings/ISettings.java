package com.vr.player.settings;

import com.vr.player.sensor.MoveManager;

/**
 * Created by qiangwang on 1/18/17.
 */

public interface ISettings {
    SettingsManager getSettingsManager();
    MoveManager getMoveManager();
    RotateLimitManager getRotateLimitManager();
}
