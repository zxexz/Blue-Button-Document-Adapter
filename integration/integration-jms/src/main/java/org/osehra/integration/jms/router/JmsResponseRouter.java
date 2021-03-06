package org.osehra.integration.jms.router;

import org.osehra.integration.core.router.Router;
import org.osehra.integration.core.router.RouterException;
import org.osehra.integration.jms.JmsTransport;
import org.osehra.integration.util.NullChecker;
import org.osehra.integration.util.StringUtil;

/**
 * JMS Service router. It derives of JmsTransport and receives messages from a
 * queue.
 * 
 * @author Julian Jewel
 */
public class JmsResponseRouter extends JmsTransport implements
		Router<Object, Object> {

	/**
	 * The message selector.
	 * 
	 * @uml.property name="selector"
	 */
	private String selector;

	/**
	 * The input message is a message identifier.
	 * 
	 * @param source
	 *            the input selector, has to be a message identifier
	 * @return the received message
	 * @throws RouterException
	 *             an exception occured when routing the message
	 */
	@Override
	public Object route(final Object source) throws RouterException {
		// Selector has to be of type string
		if (String.class.isInstance(source)
				&& NullChecker.isNotEmpty(this.selector)) {
			final String messageSelector = this.selector.replaceAll(
					StringUtil.appendEscapeSequence("{1}"), (String) source);
			return this.selectedReceive(messageSelector);
		}
		return this.receive(source);
	}

	/**
	 * Set the selector string. The message id is passed to the receive method
	 * and the selector string must contain a {1} where the input is replaced.
	 * 
	 * @param theSelector
	 *            the selector
	 * @uml.property name="selector"
	 */
	public void setSelector(final String theSelector) {
		this.selector = theSelector;
	}
}
