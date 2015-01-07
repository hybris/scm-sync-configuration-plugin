package hudson.plugins.scm_sync_configuration.utils;

//import com.google.common.hash.HashCode;
//import com.google.common.hash.HashFunction;
//import com.google.common.hash.Hashing;
//import com.google.common.hash.HashingInputStream;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

/**
 * @author fcamblor
 * Utility class allowing to provide easy access to jenkins files checksums
 */
public class Checksums {
    public static boolean fileAndByteArrayContentAreEqual(File file, byte[] content) throws IOException {
        if(!file.exists()){
            return content == null || content.length == 0;
        }

//        Checksum checksum = createChecksum();
//        long fileChecksum = Files.getChecksum(file, checksum);
//        long contentChecksum = ByteStreams.getChecksum(ByteStreams.newInputStreamSupplier(content), checksum);
//        return fileChecksum == contentChecksum;

        HashCode crc32 = Files.hash(file, Hashing.crc32());
        int fileHash = crc32.asInt();
        int contentHash = Hashing.crc32().hashBytes(content).asInt();
        return fileHash == contentHash;
    }

    private static Checksum createChecksum(){
        return new CRC32();
    }
}
