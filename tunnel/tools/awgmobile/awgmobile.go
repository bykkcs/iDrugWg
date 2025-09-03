package awgmobile

import "github.com/amnezia-vpn/amneziawg-go/device"

// Backend is exported to Java/Kotlin through gomobile bind.
type Backend struct{}

// IpcSet applies extra configuration to the running interface.
func (Backend) IpcSet(iface string, payload string) (int, error) {
    if err := device.IpcSetOn(iface, payload); err != nil {
        return -1, err
    }
    return 0, nil
}
