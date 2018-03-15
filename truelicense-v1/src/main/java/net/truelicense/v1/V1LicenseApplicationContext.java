/*
 * Copyright (C) 2005-2017 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */

package net.truelicense.v1;

import de.schlichtherle.license.LicenseContent;
import net.truelicense.api.LicenseManagementContextBuilder;
import net.truelicense.core.TrueLicenseApplicationContext;
import net.truelicense.obfuscate.Obfuscate;
import net.truelicense.v1.auth.V1RepositoryContext;
import net.truelicense.v1.codec.X500PrincipalXmlCodec;
import net.truelicense.v1.crypto.V1Encryption;

import static global.namespace.fun.io.bios.BIOS.gzip;

/**
 * The root context for applications which need to manage Version 1 (V1) format license keys.
 * This class is provided to enable applications to generate, install, verify and uninstall license keys in the format
 * for TrueLicense 1.X applications.
 * Since TrueLicense 2.0, this format is obsolete and should not be used for new applications!
 * <p>
 * This class is immutable.
 *
 * @author Christian Schlichtherle
 */
public final class V1LicenseApplicationContext extends TrueLicenseApplicationContext {

    @Obfuscate
    private static final String STORE_TYPE = "JKS";

    @Obfuscate
    private static final String PBE_ALGORITHM = "PBEWithMD5AndDES";

    @Override
    public LicenseManagementContextBuilder context() {
        return super.context()
                .codec(new X500PrincipalXmlCodec())
                .compression(gzip())
                .encryptionFunction(V1Encryption::new)
                .licenseFactory(LicenseContent::new)
                .repositoryContext(new V1RepositoryContext());
    }

    /**
     * {@inheritDoc}
     * <p>
     * The implementation in the class {@link V1LicenseApplicationContext}
     * returns {@code "PBEWithMD5AndDES"}.
     * This was the only supported PBE algorithm in TrueLicense 1 and its
     * not possible to use another one.
     */
    @Override
    public final String pbeAlgorithm() { return PBE_ALGORITHM; }

    /**
     * {@inheritDoc}
     * <p>
     * The implementation in the class {@link V1LicenseApplicationContext}
     * returns {@code "JKS"}.
     */
    @Override
    public final String storeType() { return STORE_TYPE; }
}
