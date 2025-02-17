// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2022
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.framework.daw.data;

import de.mossgrabers.framework.observer.IItemSelectionObserver;


/**
 * Interface to the master track.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public interface IMasterTrack extends ITrack
{
    /**
     * Register an observer to get notified when the master track gets de-/selected.
     *
     * @param observer The observer to register
     */
    void addSelectionObserver (IItemSelectionObserver observer);
}