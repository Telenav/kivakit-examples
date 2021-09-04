#!/bin/bash

#///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
#
#  © 2011-2021 Telenav, Inc.
#  Licensed under Apache License, Version 2.0
#
#///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

#
# Environment
#

ROOT="$(pwd)"
BRANCH="${GITHUB_REF//refs\/heads\//}"
SUPERPOM_BUILD="mvn --batch-mode --no-transfer-progress clean install"
BUILD="mvn -Dmaven.javadoc.skip=true -DKIVAKIT_DEBUG="!Debug" -P shade -P tools --no-transfer-progress --batch-mode clean install"
CLONE="git clone --branch "$BRANCH" --quiet"

#
# Build kivakit
#

echo "Cloning kivakit in $ROOT"
cd "$ROOT"
$CLONE https://github.com/Telenav/kivakit.git

echo "Installing kivakit super POM"
cd "$ROOT"/kivakit/superpom
$SUPERPOM_BUILD

echo "Building kivakit"
cd "$ROOT"/kivakit
$BUILD

#
# Build kivakit-extensions
#

echo "Cloning kivakit-extensions in $ROOT"
cd "$ROOT"
$CLONE https://github.com/Telenav/kivakit-extensions.git

echo "Building kivakit-extensions"
cd "$ROOT"/kivakit-extensions
$BUILD

#
# Build kivakit-examples
#

echo "Cloning kivakit-examples in $ROOT"
cd "$ROOT"
$CLONE https://github.com/Telenav/kivakit-examples.git

echo "Building kivakit-examples"
cd "$ROOT"/kivakit-examples
$BUILD
