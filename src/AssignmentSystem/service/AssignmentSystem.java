package AssignmentSystem.service;
import java.util.*;

/*

Following functionalities need to be implemented

- initializeAgent(agents)
- setLimit(AgentName, limit)
- assign(conversationId)
- get_assignment_queue(queuesize)
Dry run to assign next 'queuesize' conversations and return list of expected agents
-

Requirements:
- respect individual limits of each agent
- for every assignment, assign it to the agent with least conversations
- in case of tie, assign to agent that was assigned least recently
- default limit is 2
- for every a

Example:
initialiseAgent(["Bob", "Alice", "Rohan"])
 */



interface AssignmentSystem {
    void initializeAgent(List<String> agents);
    void setLimit(String AgentName, int limit);
    String assign(int convoId);
    List<String> get_assignment_queue(int queuesize);
}
