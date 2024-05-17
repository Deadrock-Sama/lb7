package lb.project.lb6_server.client.builders;


import lb.project.lb6_server.lib.ui.UIController;

abstract public class Builder {

    public Builder(UIController controller) {
        this.controller = controller;
    }

    protected final UIController controller;

}
