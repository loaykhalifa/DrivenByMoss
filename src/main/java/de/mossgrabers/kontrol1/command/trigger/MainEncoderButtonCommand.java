// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.kontrol1.command.trigger;

import de.mossgrabers.framework.ButtonEvent;
import de.mossgrabers.framework.command.core.AbstractTriggerCommand;
import de.mossgrabers.framework.daw.IModel;
import de.mossgrabers.framework.mode.ModeManager;
import de.mossgrabers.kontrol1.Kontrol1Configuration;
import de.mossgrabers.kontrol1.controller.Kontrol1ControlSurface;
import de.mossgrabers.kontrol1.mode.Modes;
import de.mossgrabers.kontrol1.mode.device.BrowseMode;


/**
 * Command for pressing the main encoder.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class MainEncoderButtonCommand extends AbstractTriggerCommand<Kontrol1ControlSurface, Kontrol1Configuration>
{
    /**
     * Constructor.
     *
     * @param model The model
     * @param surface The surface
     */
    public MainEncoderButtonCommand (final IModel model, final Kontrol1ControlSurface surface)
    {
        super (model, surface);
    }


    /** {@inheritDoc} */
    @Override
    public void execute (final ButtonEvent event)
    {
        if (event != ButtonEvent.DOWN)
            return;

        final ModeManager modeManager = this.surface.getModeManager ();
        if (modeManager.isActiveMode (Modes.MODE_PARAMS))
        {
            this.model.getCursorDevice ().toggleWindowOpen ();
            return;
        }

        if (modeManager.isActiveMode (Modes.MODE_BROWSER))
        {
            final BrowseMode mode = (BrowseMode) modeManager.getMode (Modes.MODE_BROWSER);
            mode.onValueKnobTouch (7, true);
            return;
        }

        this.model.toggleCurrentTrackBank ();
    }
}
