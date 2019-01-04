package com.sherinstephen.expressionbuilder.temp;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class NodeUUID implements Cloneable {

    private String nodeId;

    private String nodeSource;

    private String nodeMsgSource;

    private String nodeType;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeSource() {
        return nodeSource;
    }

    public void setNodeSource(String nodeSource) {
        this.nodeSource = nodeSource;
    }

    public String getNodeMsgSource() {
        return nodeMsgSource;
    }

    public void setNodeMsgSource(String nodeMsgSource) {
        this.nodeMsgSource = nodeMsgSource;
    }

    public String getNodeType() {
        return nodeType;
    }

    /**
     * @param nodeType the nodeType to set
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(ReflectionToStringBuilder.toString(this,
            ToStringStyle.MULTI_LINE_STYLE));
        sb.append("}");
        return sb.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(nodeType).append(nodeId).toHashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        NodeUUID rhs = (NodeUUID) obj;
        return new EqualsBuilder().appendSuper(super.equals(obj)).append(nodeType, rhs.nodeType).append(nodeId, rhs.nodeId).isEquals();
    }

    @Override
    protected NodeUUID clone() throws CloneNotSupportedException {
        NodeUUID clone = (NodeUUID) super.clone();
        clone.setNodeId(this.getNodeId());
        clone.setNodeSource(this.getNodeSource());
        clone.setNodeMsgSource(this.getNodeMsgSource());
        clone.setNodeType(this.getNodeType());
        return clone;
    }
}