package org.chzz.demo.viewmodel;

import org.chzz.demo.App;
import org.chzz.demo.engine.Engine;

import java.util.Map;

/**
 * Interface that every ViewModel must implement
 */
public interface BaseViewModel {
    public Engine mEngine = App.getInstance().getEngine();

    public void onUserVisible(Map<String, Object> data);

    void destroy();
}
