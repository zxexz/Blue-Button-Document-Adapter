package org.osehra.das.common.bpm.state;

import org.osehra.das.common.bpm.ConversationPhase;
import org.osehra.das.common.bpm.activity.Transition;

import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Persisted state.
 * 
 * @author Julian Jewel
 */
public class Persisted extends AbstractState {

	/**
	 * The conversation phase, ConversationPhase.RESTORE.
	 * @uml.property  name="conversationPhase"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final ConversationPhase conversationPhase = ConversationPhase.RESTORE;
	/**
	 * The correlation resolver.
	 * @uml.property  name="correlationResolver"
	 * @uml.associationEnd  
	 */
	private XPathExpression correlationResolver;

	/**
	 * The transition that invoked this activity and paused the process.
	 * @uml.property  name="transition"
	 * @uml.associationEnd  
	 */
	private Transition transition;

	/**
	 * Get the conversation phase.
	 * @return  ConversationPhase.RESTORE
	 * @uml.property  name="conversationPhase"
	 */
	@Override
	public final ConversationPhase getConversationPhase() {
		return this.conversationPhase;
	}

	/**
	 * Get the correlation resolver.
	 * @return  the correlation resolver
	 * @uml.property  name="correlationResolver"
	 */
	public final XPathExpression getCorrelationResolver() {
		return this.correlationResolver;
	}

	/**
	 * Get the transition.
	 * @return  the transition
	 * @uml.property  name="transition"
	 */
	public final Transition getTransition() {
		return this.transition;
	}

	/**
	 * Set the correlation expression.
	 * 
	 * @param theCorrelationExpression
	 *            the correlation expression
	 */
	public final void setCorrelationExpression(
			final String theCorrelationExpression) {
		try {
			this.correlationResolver = XPathFactory.newInstance().newXPath()
					.compile(theCorrelationExpression);
		} catch (final XPathExpressionException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Set the activity that is needed to continue the process.
	 * @param theTransition  the activity that is needed to continue the process
	 * @uml.property  name="transition"
	 */
	public final void setTransition(final Transition theTransition) {
		this.transition = theTransition;
	}
}
