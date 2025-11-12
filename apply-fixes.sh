#!/bin/bash
# Apply fixes for Data Binding issues
# This script would be run if --apply flag was provided

echo "Applying Data Binding fixes..."

# Fix 1: Update variable type in tunnel_detail_peer.xml
sed -i.bak 's|pw.idrug.connections.config.Peer|org.amnezia.awg.config.Peer|g' ui/src/main/res/layout/tunnel_detail_peer.xml

echo "Fixes applied successfully!"
echo "Updated tunnel_detail_peer.xml to use correct Peer type."