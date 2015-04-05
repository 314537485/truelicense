/*
 * Copyright (C) 2005-2015 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */

package org.truelicense.core;

/**
 * Provides a license.
 *
 * @author Christian Schlichtherle
 */
public interface LicenseProvider {

    /** Returns a license. */
    License license();
}