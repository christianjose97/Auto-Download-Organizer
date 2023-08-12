import java.io.File

val extensionToDir = mapOf(
    ".txt" to "TextFiles",
    ".jpg" to "ImageFiles",
    ".png" to "ImageFiles",
    ".pdf" to "PDFs"
    // Add more extensions and corresponding directory names as needed
)

fun organizeFileByType(sourceFilePath: String) {
    val sourceFile = File(sourceFilePath)
    if (!sourceFile.exists()) {
        println("Source file not found.")
        return
    }

    val fileExtension = sourceFile.extension
    val targetRootFolder = "C:/organized_downloads" // Set your desired target root folder here
    val targetSubfolderName = extensionToDir[fileExtension] ?: "OtherFiles"
    val targetSubfolder = File(targetRootFolder, targetSubfolderName)

    if (!targetSubfolder.exists()) {
        targetSubfolder.mkdirs()
    }

    val targetFilePath = File(targetSubfolder, sourceFile.name)

    if (!targetFilePath.exists()) {
        sourceFile.copyTo(targetFilePath)
        println("Moved '${sourceFile.name}' to '${targetSubfolder.absolutePath}'")
    } else {
        println("'${sourceFile.name}' already exists in '${targetSubfolder.absolutePath}'")
    }
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: kotlin ScriptName.kt sourceFilePath")
        return
    }

    val sourceFilePath = args[0]
    organizeFileByType(sourceFilePath)
}
