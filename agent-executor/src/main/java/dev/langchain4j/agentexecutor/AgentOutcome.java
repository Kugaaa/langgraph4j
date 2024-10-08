package dev.langchain4j.agentexecutor;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class AgentOutcome implements Externalizable {
    private AgentAction action;
    private AgentFinish finish;

    AgentAction action() { return action; }
    AgentFinish finish() { return finish; }

    public AgentOutcome() {}
    public AgentOutcome( AgentAction action, AgentFinish finish) {
        this.action = action;
        this.finish = finish;
    }
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        AgentAction.SERIALIZER.writeObjectNullable(action, out);
        AgentFinish.SERIALIZER.writeObjectNullable(finish, out);

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        AgentAction.SERIALIZER.readObjectNullable(in)
                    .ifPresent( value -> action = value );
        AgentFinish.SERIALIZER.readObjectNullable(in)
                    .ifPresent( value -> finish = value );
    }
}
