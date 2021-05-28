// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2021
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.controller.ni.maschine.jam.command.trigger;

import de.mossgrabers.controller.ni.maschine.jam.MaschineJamConfiguration;
import de.mossgrabers.controller.ni.maschine.jam.controller.EncoderModeManager;
import de.mossgrabers.controller.ni.maschine.jam.controller.MaschineJamControlSurface;
import de.mossgrabers.framework.command.trigger.transport.TapTempoCommand;
import de.mossgrabers.framework.daw.IModel;
import de.mossgrabers.framework.utils.ButtonEvent;
import de.mossgrabers.framework.view.Views;


/**
 * Command to tap the tempo.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class MaschineJamTapTempoCommand extends TapTempoCommand<MaschineJamControlSurface, MaschineJamConfiguration>
{
    private final EncoderModeManager encoderManager;


    /**
     * Constructor.
     *
     * @param encoderManager The encoder manager
     * @param model The model
     * @param surface The surface
     */
    public MaschineJamTapTempoCommand (final EncoderModeManager encoderManager, final IModel model, final MaschineJamControlSurface surface)
    {
        super (model, surface);

        this.encoderManager = encoderManager;
    }


    /** {@inheritDoc} */
    @Override
    public void executeShifted (final ButtonEvent event)
    {
        super.execute (event, 127);
    }


    /** {@inheritDoc} */
    @Override
    public void execute (final ButtonEvent event, final int velocity)
    {
        if (this.surface.isShiftPressed ())
        {
            this.executeShifted (event);
            return;
        }

        if (event == ButtonEvent.DOWN)
        {
            this.surface.getViewManager ().setTemporary (Views.TEMPO);
            this.encoderManager.enableTemporaryEncodeMode (EncoderMode.TEMPORARY_TEMPO);
        }
        else if (event == ButtonEvent.UP)
        {
            this.surface.getViewManager ().restore ();
            this.encoderManager.disableTemporaryEncodeMode ();
        }
    }
}