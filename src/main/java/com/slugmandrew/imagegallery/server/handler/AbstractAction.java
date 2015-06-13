package com.slugmandrew.imagegallery.server.handler;

import com.allen_sauer.gwt.log.client.Log;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.rpc.shared.Action;
import com.gwtplatform.dispatch.rpc.shared.Result;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Simple abstract super-class for {@link com.gwtplatform.dispatch.server.actionhandler.ActionHandler} implementations
 * that forces the {@link com.gwtplatform.dispatch.rpc.shared.Action} class to be passed in as a constructor to the handler.
 * 
 * @param <A>
 *        The {@link com.gwtplatform.dispatch.rpc.shared.Action} type.
 * @param <R>
 *        The {@link com.gwtplatform.dispatch.rpc.shared.Result} type.
 */
public abstract class AbstractAction<A extends Action<R>, R extends Result> implements ActionHandler<A, R>
{
	
	private final Class<A> actionType;
	
	public AbstractAction(Class<A> actionType)
	{
		this.actionType = actionType;
	}
	
	public Class<A> getActionType()
	{
		return actionType;
	}
	
	// my method to log exceptions
	protected void logException(Exception e)
	{
		Log.error("Exception caught in " + this.getClass().getSimpleName(), e);
	}
	
	
	
	@Override
	public void undo(A action, R result, ExecutionContext context) throws ActionException
	{
	}
}
