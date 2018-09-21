
Pod::Spec.new do |s|
  s.name         = "RNSecureStorage"
  s.version      = "1.0.7"
  s.summary      = "RNSecureStorage"
  s.description  = <<-DESC
                    Secure Storage for React Native (Android & iOS)
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "Talut Tasgiran" => "info@taluttasgiran.com.tr" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/talut/rn-secure-storage.git", :tag => "master" }
  s.source_files  = "RNSecureStorage/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  