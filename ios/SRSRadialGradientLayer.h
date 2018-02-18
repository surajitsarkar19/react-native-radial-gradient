#import <Foundation/Foundation.h>
#import <QuartzCore/QuartzCore.h>
#import <UIKit/UIKit.h>

@interface SRSRadialGradientLayer : CALayer

@property (nullable, nonatomic, strong) NSArray<UIColor *> *colors;
@property (nullable, nonatomic, strong) NSArray<NSNumber *> *locations;
@property (nonatomic) CGPoint startCenter;
@property (nonatomic) CGPoint endCenter;
@property (nonatomic) CGFloat startRadius;
@property (nonatomic) CGFloat endRadius;

@end
